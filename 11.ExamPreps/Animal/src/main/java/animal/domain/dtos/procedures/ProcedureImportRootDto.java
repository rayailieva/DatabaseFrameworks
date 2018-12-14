package animal.domain.dtos.procedures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "procedures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureImportRootDto {

    @XmlElement(name = "procedure")
    private ProcedureImportDto[] procedureImportDtos;

    public ProcedureImportRootDto() {
    }

    public ProcedureImportDto[] getProcedureImportDtos() {
        return this.procedureImportDtos;
    }

    public void setProcedureImportDtos(ProcedureImportDto[] procedureImportDtos) {
        this.procedureImportDtos = procedureImportDtos;
    }
}
