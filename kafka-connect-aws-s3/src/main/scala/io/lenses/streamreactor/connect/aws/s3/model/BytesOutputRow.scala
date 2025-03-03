/*
 * Copyright 2020 Lenses.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.lenses.streamreactor.connect.aws.s3.model

import io.lenses.streamreactor.connect.aws.s3.formats.bytes.ByteArrayUtils

import scala.collection.mutable.ListBuffer

case class BytesOutputRow(
                           keySize: Option[Long],
                           valueSize: Option[Long],
                           key: Array[Byte],
                           value: Array[Byte],
                           bytesRead: Option[Int] = None
                         ) {

  def toByteArray: Array[Byte] = {
    val buffer = new ListBuffer[Byte]()

    keySize.foreach {
      buffer ++= ByteArrayUtils.longToByteArray(_)
    }
    valueSize.foreach {
      buffer ++= ByteArrayUtils.longToByteArray(_)
    }

    if (key.nonEmpty) buffer ++= key
    if (value.nonEmpty) buffer ++= value
    buffer.toArray
  }

}
