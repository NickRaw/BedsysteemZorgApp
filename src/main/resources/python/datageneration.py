import uuid
import random
import names


departementInsert = """
INSERT INTO DEPARTMENT VALUES ('##ID##', '##NAME##');
"""
patientInsert = """
INSERT INTO PATIENT VALUES ('##ID##', '##AGE##', '##FIRSTNAME##', '##LASTNAME##', '600', '##BED_ID##', '##DEPARTMENT_ID##');
"""
bedInsert = """
INSERT INTO BED VALUES ('##ID##', '##DEPARTMENT_ID##', NULL);
"""
bedUpdate = """
UPDATE BED SET PATIENT_ID = '##PATIENT_ID##' WHERE ID = '##BED_ID##';
"""
departmentBedInsert = """
INSERT INTO DEPARTMENT_BEDS VALUES ('##DEPARTMENT_ID##', '##BED_ID##');
"""
departmentPatientInsert = """
INSERT INTO DEPARTMENT_PATIENTS VALUES ('##DEPARTMENT_ID##', '##PATIENT_ID##');
"""

departement = [str(uuid.uuid1()), "Kinderafdeling"]
beds = []
patients = []

bedAmount = 10
for x in range(0, bedAmount):
    bed = [str(uuid.uuid1()), departement[0], None]
    beds.append(bed)
    print(bed[0], " made")

patientAmount = 8
for x in range(0, patientAmount):
    bedId = ""
    bedIsGood = False
    while not bedIsGood:
        bedId = beds[random.randint(0, len(beds)-1)][0]
        for singleBed in beds:
            if singleBed[0] == bedId:
                if singleBed[2] == None:
                    bedIsGood = True

    bed = []
    patient = [str(uuid.uuid1()), random.randint(3, 15), names.get_first_name(), names.get_last_name(), bedId, departement[0]]
    for singleBed in beds:
        if singleBed[0] == bedId:
            singleBed[2] = patient[0]
            bed = singleBed
    print(patient)
    print(bed)
    print()
    patients.append(patient)

with open("../data/testdata.sql", "a") as file:
    departementString = departementInsert.replace("##ID##", departement[0])
    departementString = departementString.replace("##NAME##", departement[1])

    file.write(departementString)

    for bed in beds:
        bedString = bedInsert.replace("##ID##", bed[0])
        bedString = bedString.replace("##DEPARTMENT_ID##", bed[1])
        file.write(bedString)

    for patient in patients:
        patientString = patientInsert.replace("##ID##", patient[0])
        patientString = patientString.replace("##AGE##", str(patient[1]))
        patientString = patientString.replace("##FIRSTNAME##", patient[2])
        patientString = patientString.replace("##LASTNAME##", patient[3])
        patientString = patientString.replace("##BED_ID##", patient[4])
        patientString = patientString.replace("##DEPARTMENT_ID##", patient[5])
        file.write(patientString)

        depPat = departmentPatientInsert.replace('##PATIENT_ID##', patient[0])
        depPat = depPat.replace('##DEPARTMENT_ID##', departement[0])
        file.write(depPat)

    for bed in beds:
        depBed = departmentBedInsert.replace('##BED_ID##', bed[0])
        depBed = depBed.replace('##DEPARTMENT_ID##', departement[0])
        file.write(depBed)

        if bed[2] != None:
            bedString = bedUpdate.replace("##BED_ID##", bed[0])
            bedString = bedString.replace("##PATIENT_ID##", bed[2])
            file.write(bedString)
