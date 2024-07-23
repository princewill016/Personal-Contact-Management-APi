package manager.Logging;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LoggingRepository extends ElasticsearchRepository<LoggingEntity, String> {
}