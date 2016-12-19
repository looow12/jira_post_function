def dropDown = getFieldById("customfield_14626")

def scriptCF = getFieldById("customfield_14617")
def documentationCF = getFieldById("customfield_14618");

def columnNameCF = getFieldById("customfield_14610")
def typeCF = getFieldById("customfield_14611")
def defaultValueCF = getFieldById("customfield_14612")
def nullableValueCF = getFieldById("customfield_14613")
def specificRowNumCF = getFieldById("customfield_14614")
def colDefCF = getFieldById("customfield_14615")
def optionsCF = getFieldById("customfield_14616")
def tableNameCF = getFieldById("customfield_14609")
def ReleaseIdDF = getFieldById("customfield_14602")
def ReleaseNameCF = getFieldById("customfield_14603")
def ReleaseDateCF = getFieldById("customfield_14604")
def BaseReleaseIdCF = getFieldById("customfield_14605")
def FrcReleaseIdCF = getFieldById("customfield_14606")
def AutomRollbackCF = getFieldById("customfield_14607")

log.debug("dropdown value" + dropDown.getValue())
String DbtDeLigne = "'";
String FinDeLigneElse = "' ', "; 
String FinDeLigneElseDernier = "' '); "; 
String FinDeLigne = "', ";
String FinDeLigneDernier = "'); ";

//Fields always appaear
String TextReleaseIdDF = ReleaseIdDF.getValue()?.toString();
String TextReleaseNameCF = ReleaseNameCF.getValue()?.toString();
String TextReleaseDateCF = ReleaseDateCF.getValue()?.toString();
String TextBaseReleaseIdCF = BaseReleaseIdCF.getValue()?.toString();    
String TextAutomRollbackCF = AutomRollbackCF.getValue()?.toString();

/*if(FrcReleaseIdCF == true){
    String TextFrcReleaseIdCF = "Y -"; 
} else if(FrcReleaseIdCF == false) {
    String TextFrcReleaseIdCF = "N -"; 
}else {
    String TextFrcReleaseIdCF = " - ";
} */

def TextFrcReleaseIdCF = FrcReleaseIdCF.getValue(); 
 
if (dropDown.getValue() == "SQL.Create a new Table") {
    //Mandatory fields
    ReleaseIdDF.setHidden(false);
    ReleaseNameCF.setHidden(false);
    ReleaseDateCF.setHidden(false);
    BaseReleaseIdCF.setHidden(false);
    FrcReleaseIdCF.setHidden(false);
    AutomRollbackCF.setHidden(false);
    //Fields not required
    scriptCF.setHidden(false);
    documentationCF.setHidden(false);
    colDefCF.setHidden(true);
    optionsCF.setHidden(true);
    //Fields Required
    setCustomFieldRequirment(columnNameCF);
    setCustomFieldRequirment(typeCF);
    defaultValueCF.setHidden(false);
    nullableValueCF.setHidden(false);
    specificRowNumCF.setHidden(false);    
    tableNameCF.setHidden(false);
    
    
    //Only fields for "SQL.Create a new Table"
    String TexttableNameCFF = tableNameCF.getValue()?.toString();
    String TextcolumnNameCF = columnNameCF.getValue()?.toString();
    String TexttypeCF = typeCF.getValue()?.toString();
    String TextdefaultValueCF = defaultValueCF.getValue()?.toString();
    String TextspecificRowNumCF = specificRowNumCF.getValue()?.toString();
    
    def TextnullableValueCF = nullableValueCF.getValue();
    
 //Field Documentation
    String DocumentationString="";
    DocumentationString = "NEW SCHEMA OBJECT : \n";
    if(TextReleaseIdDF!=""){
        DocumentationString = DocumentationString + TextReleaseIdDF+ " - "; 
    } else {DocumentationString = DocumentationString + " - "; }
    if(TextReleaseNameCF!=""){
        DocumentationString = DocumentationString + TextReleaseNameCF + " - "; 
    }else {DocumentationString = DocumentationString + " - "; }
    if(TextReleaseDateCF!=""){
        DocumentationString = DocumentationString + TextReleaseDateCF + " - "; 
    }else {DocumentationString = DocumentationString + " - "; }
    if(TextBaseReleaseIdCF!=""){
        DocumentationString = DocumentationString + TextBaseReleaseIdCF + " - "; 
    }else {DocumentationString = DocumentationString + " - "; }
    if(TextFrcReleaseIdCF!=""){
        DocumentationString = DocumentationString + TextFrcReleaseIdCF + " - "; 
    }else {DocumentationString = DocumentationString + " - "; }    
    if(TextAutomRollbackCF!=""){
        DocumentationString = DocumentationString + TextAutomRollbackCF; 
    }else {DocumentationString = DocumentationString + "  "; }

    DocumentationString = DocumentationString + "\nCOMMAND :" + "\nADD_TABLE_COLUMN("; 
   
    if(TexttableNameCFF!=""){
        DocumentationString = DocumentationString + DbtDeLigne + TexttableNameCFF + FinDeLigne; 
    }else {DocumentationString = DocumentationString + FinDeLigneElse }
    if(TextcolumnNameCF!=""){
        DocumentationString = DocumentationString +DbtDeLigne+ TextcolumnNameCF + FinDeLigne; 
    }else {DocumentationString = DocumentationString + FinDeLigneElse }
    if(TexttypeCF!=""){
        DocumentationString = DocumentationString +DbtDeLigne+ TexttypeCF + FinDeLigne; 
    }else {DocumentationString = DocumentationString + FinDeLigneElse }
    if(TextdefaultValueCF!=""){
        DocumentationString = DocumentationString +DbtDeLigne+ TextdefaultValueCF + FinDeLigne; 
    }else {DocumentationString = DocumentationString + FinDeLigneElse }
    if(TextnullableValueCF!=""){
        DocumentationString = DocumentationString +DbtDeLigne+ TextnullableValueCF + FinDeLigne; 
    }else {DocumentationString = DocumentationString + FinDeLigneElse }    
    if(TextspecificRowNumCF!=""){
        DocumentationString = DocumentationString +DbtDeLigne+ TextspecificRowNumCF + FinDeLigneDernier; 
    }else {DocumentationString = DocumentationString + FinDeLigneElseDernier }

    documentationCF.setFormValue(DocumentationString);
    
    //Field Script
    
    String ScriptString="";
    ScriptString = "BEGIN \n RS2_UTIL.BEGIN_JOB_RELEASE(";
    if(TextReleaseIdDF!=""){
        ScriptString = ScriptString + DbtDeLigne +TextReleaseIdDF+ FinDeLigne; 
    } else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextReleaseNameCF!=""){
        ScriptString = ScriptString + DbtDeLigne + TextReleaseNameCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextReleaseDateCF!=""){
        ScriptString = ScriptString + DbtDeLigne +TextReleaseDateCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextBaseReleaseIdCF!=""){
        ScriptString = ScriptString + DbtDeLigne +TextBaseReleaseIdCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextFrcReleaseIdCF!=""){
        ScriptString = ScriptString + DbtDeLigne +TextFrcReleaseIdCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextAutomRollbackCF!=""){
        ScriptString = ScriptString + DbtDeLigne + TextAutomRollbackCF + FinDeLigneDernier; 
    }else {ScriptString = ScriptString + FinDeLigneElseDernier }
    
    ScriptString = ScriptString +"\n RS2_UTIL.ADD_TABLE_COLUMN(";


    if(TexttableNameCFF!=""){
        ScriptString = ScriptString +DbtDeLigne+ TexttableNameCFF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextcolumnNameCF!=""){
        ScriptString = ScriptString +DbtDeLigne+ TextcolumnNameCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TexttypeCF!=""){
        ScriptString = ScriptString +DbtDeLigne+ TexttypeCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse}
    if(TextdefaultValueCF!=""){
        ScriptString = ScriptString +DbtDeLigne+ TextdefaultValueCF + FinDeLigne; 
    }else {ScriptString = ScriptString +  FinDeLigneElse }
    if(TextnullableValueCF!=""){
        ScriptString = ScriptString +DbtDeLigne+ TextnullableValueCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextspecificRowNumCF!=""){
        ScriptString = ScriptString +DbtDeLigne+ TextspecificRowNumCF + FinDeLigneDernier; 
    }else {ScriptString = ScriptString + FinDeLigneElseDernier } 

    
    ScriptString = ScriptString +"\n RS2_UTIL.END_JOB_RELEASE(";
    ScriptString = ScriptString +"'END JOB');";
    
    ScriptString = ScriptString + "\nEND;";
    scriptCF.setFormValue(ScriptString);

}else if (dropDown.getValue() == "SQL.Create a new Column in a Table") {
    ReleaseIdDF.setHidden(false);
    ReleaseNameCF.setHidden(false);
    ReleaseDateCF.setHidden(false);
    BaseReleaseIdCF.setHidden(false);
    FrcReleaseIdCF.setHidden(false);
    AutomRollbackCF.setHidden(false);
    scriptCF.setHidden(false);
    documentationCF.setHidden(false);
    columnNameCF.setHidden(true);
    typeCF.setHidden(true);
    defaultValueCF.setHidden(true);
    nullableValueCF.setHidden(true);
    specificRowNumCF.setHidden(false);
    colDefCF.setHidden(false);
    optionsCF.setHidden(false);
    tableNameCF.setHidden(false);
    
    
    //Only fields for "Create a new Column in a Table"
    String TexttableNameCF = tableNameCF.getValue()?.toString();
    String TextColDefCF = colDefCF.getValue()?.toString();
    String TextspecificRowNumCF = specificRowNumCF.getValue()?.toString();
    String TextoptionsCF = optionsCF.getValue()?.toString();


    //Field Documentation
    String DocumentationString="";
    DocumentationString = "NEW SCHEMA OBJECT : \n";
    if(TextReleaseIdDF!=""){
        DocumentationString = DocumentationString + TextReleaseIdDF+ " - "; 
    } else {DocumentationString = DocumentationString + " - "; }
    if(TextReleaseNameCF!=""){
        DocumentationString = DocumentationString + TextReleaseNameCF + " - "; 
    }else {DocumentationString = DocumentationString + " - "; }
    if(TextReleaseDateCF!=""){
        DocumentationString = DocumentationString + TextReleaseDateCF + " - "; 
    }else {DocumentationString = DocumentationString + " - "; }
    if(TextBaseReleaseIdCF!=""){
        DocumentationString = DocumentationString + TextBaseReleaseIdCF + " - "; 
    }else {DocumentationString = DocumentationString + " - "; }
    if(TextFrcReleaseIdCF!=""){
        DocumentationString = DocumentationString + TextFrcReleaseIdCF + " - "; 
    }else {DocumentationString = DocumentationString + " - "; }
    if(TextAutomRollbackCF!=""){
        DocumentationString = DocumentationString + TextAutomRollbackCF; 
    }else {DocumentationString = DocumentationString + "  "; }

    DocumentationString = DocumentationString + "\nCOMMAND :" + "\nADD_TABLE_COLUMN("; 
   
    if(TexttableNameCF!=""){
        DocumentationString = DocumentationString +DbtDeLigne+ TexttableNameCF + FinDeLigne; 
    }else {DocumentationString = DocumentationString + FinDeLigneElse }
    if(TextspecificRowNumCF!=""){
        DocumentationString = DocumentationString +DbtDeLigne+ TextspecificRowNumCF + FinDeLigne; 
    }else {DocumentationString = DocumentationString + FinDeLigneElse }
    if(TextoptionsCF!=""){
        DocumentationString = DocumentationString +DbtDeLigne+ TextoptionsCF +FinDeLigneDernier; 
    }else {DocumentationString = DocumentationString + FinDeLigneElseDernier }
    DocumentationString = DocumentationString + "\nDESCRIPTION : \n";

    if(TextColDefCF!=""){
        DocumentationString = DocumentationString + TextColDefCF; 
    }
    documentationCF.setFormValue(DocumentationString);
    
    
    //Field Script
    
    String ScriptString="";
    ScriptString = "BEGIN \n RS2_UTIL.BEGIN_JOB_RELEASE(";
    if(TextReleaseIdDF!=""){
        ScriptString = ScriptString + DbtDeLigne +TextReleaseIdDF+ FinDeLigne; 
    } else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextReleaseNameCF!=""){
        ScriptString = ScriptString + DbtDeLigne + TextReleaseNameCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextReleaseDateCF!=""){
        ScriptString = ScriptString + DbtDeLigne +TextReleaseDateCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextBaseReleaseIdCF!=""){
        ScriptString = ScriptString + DbtDeLigne +TextBaseReleaseIdCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }    
    if(TextFrcReleaseIdCF!=""){
        ScriptString = ScriptString + DbtDeLigne +TextFrcReleaseIdCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextAutomRollbackCF!=""){
        ScriptString = ScriptString + DbtDeLigne + TextAutomRollbackCF + FinDeLigneDernier; 
    }else {ScriptString = ScriptString + FinDeLigneElseDernier}
    
    ScriptString = ScriptString +"\n RS2_UTIL.ADD_TABLE_COLUMN(";

    if(TexttableNameCF!=""){
        ScriptString = ScriptString +DbtDeLigne+ TexttableNameCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextspecificRowNumCF!=""){
        ScriptString = ScriptString +DbtDeLigne+ TextspecificRowNumCF + FinDeLigne; 
    }else {ScriptString = ScriptString + FinDeLigneElse }
    if(TextoptionsCF!=""){
        ScriptString = ScriptString +DbtDeLigne+ TextoptionsCF + FinDeLigneDernier; 
    }else {ScriptString = ScriptString + FinDeLigneElseDernier }

    
    ScriptString = ScriptString +"\n RS2_UTIL.END_JOB_RELEASE(";
    ScriptString = ScriptString +"'END JOB');";
    
    ScriptString = ScriptString + "\nEND;";
    scriptCF.setFormValue(ScriptString);
 
}else{
    //Mandatory fields
    ReleaseIdDF.setHidden(false);
    ReleaseNameCF.setHidden(false);
    ReleaseDateCF.setHidden(false);
    BaseReleaseIdCF.setHidden(false);
    FrcReleaseIdCF.setHidden(false);
    AutomRollbackCF.setHidden(false);
    scriptCF.setHidden(true);
    documentationCF.setHidden(true);
    
    //Unrequried fields
    tableNameCF.setHidden(true);
    columnNameCF.setHidden(true);
    typeCF.setHidden(true);
    defaultValueCF.setHidden(true);
    nullableValueCF.setHidden(true);
    specificRowNumCF.setHidden(true);
    colDefCF.setHidden(true);
    optionsCF.setHidden(true);
}

void setCustomFieldRequirment(def CF){
    CF.setHidden(false);
    CF.setRequired(true);
}