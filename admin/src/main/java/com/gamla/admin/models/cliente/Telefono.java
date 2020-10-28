package com.gamla.admin.models.cliente;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Owner on 20/07/2017.
 */
@Entity
public class Telefono {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long telefonoId;
    private Boolean predeterminado;
    private String numeroTelefonico;

    private Date reg_sistema;

    @OneToMany(mappedBy = "telefono")
    private List<ClienteTelefono> clienteTelefono;

}
