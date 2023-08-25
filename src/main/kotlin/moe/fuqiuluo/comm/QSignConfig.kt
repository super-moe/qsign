@file:OptIn(ExperimentalSerializationApi::class)
package moe.fuqiuluo.comm

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import top.mrxiaom.qsign.QSignService.Factory.Companion.CONFIG

@Serializable
data class Server(
    var host: String,
    var port: Int
)

@Serializable
data class EnvData(
    var uin: Long,
    @JsonNames("androidId", "android_id", "imei")
    var androidId: String,
    var guid: String,
    var qimei36: String,

    var packageName: String = CONFIG.protocol.packageName ?: "com.tencent.mobileqq",
    var qua: String = CONFIG.protocol.qua,
    var version: String = CONFIG.protocol.version,
    var code: String = CONFIG.protocol.code
)

@Serializable
data class Protocol(
    @SerialName("package_name")
    var packageName: String? = null,
    var qua: String,
    var version: String,
    var code: String
)

@Serializable
data class UnidbgConfig(
    var dynarmic: Boolean,
    var unicorn: Boolean,
    var debug: Boolean,
)

@Serializable
data class QSignConfig(
    var server: Server,
    var key: String,
    @JsonNames("autoRegister", "auto_register")
    var autoRegister:Boolean,
    //@JsonNames("reloadInterval", "reload_interval")
    //var reloadInterval: Int,
    var protocol: Protocol,
    var unidbg: UnidbgConfig,
    @JsonNames("blackList", "black_list")
    var blackList: List<Long>? = null
)

fun QSignConfig.checkIllegal() {
    require(server.port in 1 .. 65535) { "Port is out of range." }
    //require(reloadInterval in 20 .. 50) { "ReloadInterval is out of range." }
}