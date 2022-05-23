package zone.yue.message_board

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository

@Repository
@EnableJpaRepositories
interface BoardRepository : JpaRepository<MessageEntity, Long>