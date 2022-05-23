package zone.yue.message_board

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.web.bind.annotation.*


@RestController
class ApiController(val repository: BoardRepository) {
    /**
     * 获取所有留言
     */
    @GetMapping("/getMessages")
    fun getMessages(): List<MessageEntity> {
        return repository.findAll()
    }

    /**
     * 通过 ID 查找单条留言
     * @param Id
     */
    @GetMapping("/getMessage")
    fun getMessage(
        @RequestParam(required = true) Id: Long
    ): MessageEntity? {
        return try {
            repository.findById(Id).get()
        } catch (e: NoSuchElementException) {
            println(e.localizedMessage)
            null
        }
    }

    /**
     * 发布一条留言
     * @param NickName 昵称
     * @param Message 留言
     */
    @PutMapping("/putMessage")
    fun putMessage(
        @RequestParam(defaultValue = "游客") NickName: String,
        @RequestParam(defaultValue = "到此一游~") Message: String
    ): String {
        repository.save(MessageEntity(NickName, Message))
        return "发布成功"
    }

    /**
     * 通过 ID 删除单条留言
     * @param Id
     */
    @DeleteMapping("/deleteMessage")
    fun deleteMessage(
        @RequestParam(required = true) Id: Long
    ): String {
        return try {
            repository.deleteById(Id)
            "删除成功"
        } catch (e: EmptyResultDataAccessException) {
            e.localizedMessage
        }
    }
}