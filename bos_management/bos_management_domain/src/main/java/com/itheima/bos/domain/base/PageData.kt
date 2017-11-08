package com.itheima.bos.domain.base

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */
data  class  PageData<T>(var total:Long,var rows:List<T> )