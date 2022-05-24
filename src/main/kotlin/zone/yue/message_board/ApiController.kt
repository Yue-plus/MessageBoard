package zone.yue.message_board

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.web.bind.annotation.*
import java.net.http.HttpRequest


@RestController
@CrossOrigin // 允许跨域请求
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
     * @param message form-data
     */
    @PutMapping("/putMessage")
    fun putMessage(message: MessageEntity): String {
        if (message.NickName == "") return "昵称不能为空"
        if (message.Message == "") return "留言不能为空"
        repository.save(message)
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