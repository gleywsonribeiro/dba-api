package com.dba.dbaapi.model;

import javax.persistence.*;

@Entity
public class UserDocumentation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] document;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    public UserDocumentation() {
    }

    public UserDocumentation(Long id, byte[] document, TipoDocumento tipoDocumento, User user) {
        this.id = id;
        this.document = document;
        this.tipoDocumento = tipoDocumento;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getTipoDocumento() {
        return tipoDocumento.toString();
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = TipoDocumento.valueOf(tipoDocumento);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDocumentation)) return false;

        UserDocumentation that = (UserDocumentation) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
