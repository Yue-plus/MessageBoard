package zone.yue.message_board

import javax.persistence.*

@Entity
data class MessageEntity(
    val NickName: String = "游客",
    val Message: String = "到此一游~",
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long? = null
}