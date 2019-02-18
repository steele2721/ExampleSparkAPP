package com.steeleEnterprise.robert.spark.utils

import java.util.logging.Logger


object DFUtils extends Serializable {

    @transient lazy val logger = Logger.getLogger(getClass.getName)

    // this is where all dataframe udfs will reside
}
