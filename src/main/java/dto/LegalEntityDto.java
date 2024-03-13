package dto;

public class LegalEntityDto {

    private String companyCode;
    private String companyCodeName;
    private String cityName;

    public LegalEntityDto() {

    }

    public LegalEntityDto(String companyCode, String companyCodeName, String cityName) {
        this.companyCode = companyCode;
        this.companyCodeName = companyCodeName;
        this.cityName = cityName;
    }


    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCodeName() {
        return companyCodeName;
    }

    public void setCompanyCodeName(String companyCodeName) {
        this.companyCodeName = companyCodeName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "LegalEntityDto{" +
                "companyCode='" + companyCode + '\'' +
                ", companyCodeName='" + companyCodeName + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
