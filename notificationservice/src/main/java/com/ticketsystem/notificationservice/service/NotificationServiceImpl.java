package com.ticketsystem.notificationservice.service;

import com.ticketsystem.servicecommunication.messaging.TicketNotification;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Sink.class) //Kuyruğa client olacak. Kuyruğun konfigurasyonunu ConfigServer'dan uygulama alacak.
public class NotificationServiceImpl implements NotificationService{

    //Hangi Queue dinlediğini konfigürasyondan biliyoruz.
    //Kuyruğu dinlediği için mesaj yazıldığında bu metot tetiklenecek.

    @StreamListener(Sink.INPUT)
    @Override
    public void onNotification(TicketNotification ticketNotification){ //ortak library içindeki class
        System.out.println("Notification alındı.İlgili kullanıcıya bilgilendirme mesajı geçiliyor.");
        System.out.println("Notification : "+ticketNotification.toString()); //TicketNotification tipindeki mesajı alıp console bastık.

        //TODO: Mail atma vb. kod eklenecek.
    }
}
