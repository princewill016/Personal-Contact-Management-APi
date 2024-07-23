package manager.Logging;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Document(indexName = "my_index")
@Data
public class LoggingEntity {
      @Id
      private String id;
      private String name;
   }