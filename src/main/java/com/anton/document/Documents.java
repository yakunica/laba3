package com.anton.document;


import com.anton.client.Client;

import com.anton.documentType.DocumentType;

import com.anton.operators.Operators;
import com.anton.verificationTypes.VerificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
@Data
@Builder
public class Documents implements Serializable {
    @Id
    @GeneratedValue
    private Integer document_id;
    private String date_uploaded;
    private String document_number;
    private String date_verified;
    private String expiry_date;
    private String document_issuer;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "document_type_document_type_id", referencedColumnName = "document_type_id")
    private DocumentType documentType;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "status_status_id", referencedColumnName = "status_id")
    private DocumentStatus status;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "client_client_id", referencedColumnName = "client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "operator_operator_id", referencedColumnName = "operator_id")
    private Operators operator;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "verification_type_verification_type_id", referencedColumnName = "verification_type_id")
    private VerificationType verificationType;


    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}