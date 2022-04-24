package kr.co.mfort.`is`.nice.common.encoding

import org.apache.commons.codec.digest.DigestUtils

fun sha256Encode(plainText: String) = DigestUtils.sha256Hex(plainText)
