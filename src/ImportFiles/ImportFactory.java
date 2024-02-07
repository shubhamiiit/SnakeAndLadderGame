package ImportFiles;

public class ImportFactory {
    public ImportType importFileType(String input){
        switch (input) {
            case "JSON":
                return new ImportJSON();
            case "YML":
                return new ImportYML();
            default:
                return null;
        }
    }
}
