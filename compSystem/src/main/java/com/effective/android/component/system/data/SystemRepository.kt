package com.effective.android.component.system.data

import com.effective.android.component.blog.Chapter
import com.effective.android.component.system.Sdks
import com.effective.android.service.net.BaseListResult
import com.effective.android.service.net.BaseResult
import com.effective.android.service.net.Type
import com.effective.android.component.system.bean.Article
import io.reactivex.Flowable

class SystemRepository {


    private val paccountsApis by lazy {
        Sdks.serviceNet.service(
                SystemApis.BASE_URL, Type.GSON, SystemApis::class.java)
    }


    companion object {
        private var instance: SystemRepository? = null
            get() {
                if (field == null) {
                    field = SystemRepository()
                }
                return field
            }

        @Synchronized
        fun get(): SystemRepository {
            return instance!!
        }
    }

    fun getTreeChapters(): Flowable<BaseResult<List<Chapter>>> = paccountsApis.getTreeChapters()

    fun getArticles(id: String, pageCount: String): Flowable<BaseListResult<Article>> = paccountsApis.getArticles(id, pageCount)
}