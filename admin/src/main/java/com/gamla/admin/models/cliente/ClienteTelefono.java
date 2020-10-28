package com.gamla.admin.models.cliente;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Owner on 20/07/2017.
 */
@Entity
public class ClienteTelefono {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long clienteTelefonoId;

    private String telefono_Nombre;
    private Date reg_sistema;

    @ManyToOne
    @JoinColumn(name="telefonoId")
    private Telefono telefono;

    @ManyToOne
    @JoinColumn(name="clienteId")
    private Cliente cliente;
}
