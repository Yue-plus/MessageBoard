package zone.yue.message_board

import org.hibernate.Hibernate
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as MessageEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , NickName = $NickName , Message = $Message )"
    }
}