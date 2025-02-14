package vinistef.clinic_api.dto;

public record RegisterPatientDto(String name, String email, String cellphone, String cpf, AddressDataDto addressDto) {
}
