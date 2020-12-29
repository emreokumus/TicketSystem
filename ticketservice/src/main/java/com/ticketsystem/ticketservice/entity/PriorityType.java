package com.ticketsystem.ticketservice.entity;

import lombok.Getter;

@Getter
public enum PriorityType {
    URGENT("Acil"),
    LOW("Düşük Öncelikli"),
    HIGH("Yüksek Öncelikli");

    //Her birine label koyucaz. Kullanıcı yukarıdaki yazıyı görmeyecek labelini görecek combobox vs. bi yerde.
    //Ama biz backend kısmında yukarıdaki şeklinde çalışıcaz. Veritabanında ise 0,1,2 diye kullanıcaz.
    //3 katmanda da farklı şekilde kullanıcaz.
    private String label; //Label'i alıp DTO koyup DTO'dan label'i göstericez

    PriorityType(String label){
        this.label=label;
    }
}
