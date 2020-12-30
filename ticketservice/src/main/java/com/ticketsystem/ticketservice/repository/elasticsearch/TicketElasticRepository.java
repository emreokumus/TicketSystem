package com.ticketsystem.ticketservice.repository.elasticsearch;
        import com.ticketsystem.ticketservice.entity.elasticsearch.TicketModel;
        import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
        import org.springframework.stereotype.Repository;

//ElasticSearchde temel işlevlerimi yapacak search, insert, update gibi.
@Repository
public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel,String> {
}
