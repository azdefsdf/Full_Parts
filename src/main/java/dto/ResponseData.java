package dto;


public class ResponseData {
    private String jsonData;
    private String pdfData;
    private String errorMessage;

    // Constructors

    public ResponseData(String jsonData, String b64) {
        this.jsonData = jsonData;
        this.pdfData = b64;
    }

    public ResponseData(String jsonData, String pdfData, String errorMessage) {
        this.jsonData = jsonData;
        this.pdfData = pdfData;
        this.errorMessage = errorMessage;
    }

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getPdfData() {
		return pdfData;
	}

	public void setPdfData(String pdfData) {
		this.pdfData = pdfData;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

    
    // Getters and setters
}

 
 

 // Getters and setters