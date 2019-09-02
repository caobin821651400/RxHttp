package com.app.kotlin.rxhttp

/**
 * author : caobin
 * e-mail : 821651400@qq.com
 * time   : 2018/01/18
 * desc   :
 */
class NewsResp {

    /**
     * stat : 1
     */

    var stat: String? = null
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * uniquekey : 59a692e6638db9422e9fd3ac3884f774
         */

        var uniquekey: String? = null
        var title: String? = null
        var date: String? = null
        var category: String? = null
        var author_name: String? = null
        var url: String? = null
        var thumbnail_pic_s: String? = null
        var thumbnail_pic_s02: String? = null
        var thumbnail_pic_s03: String? = null
    }
}
