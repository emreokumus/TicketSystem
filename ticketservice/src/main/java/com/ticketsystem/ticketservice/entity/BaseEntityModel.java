package com.ticketsystem.ticketservice.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass //Data JPA'de kalıtım yoluyla bir nesneyi diğer entitylere kazandırmak istiyorsak yani parametreleri diğer entity tablolarına dahil ediyor.
public abstract class BaseEntityModel implements Serializable {

    //@CreatedBy //Security'i implemente ettiğimizde bu nesnesi kim değiştirdiğini sessiondan alması için burada user fieldi olsaydı kullanılabilirdi.

    @CreatedDate
    @Column(name="created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updateAt;
}
