package com.irinushirka.roles;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements Serializable  {
    @Id
    @GeneratedValue
    public Integer role_id;
    public String role_name;

    public Role(int role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }
}
