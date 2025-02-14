package vinistef.clinic_api.dto;

public record UpdateDoctorDto(
        String name,
        String cellphone,
        AddressDataDto addressDataDto) {
}
