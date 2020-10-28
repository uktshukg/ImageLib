package com.dexter.dunzo.ui.main.api

import com.dexter.dunzo.ui.main.api.models.Photo
import com.dexter.dunzo.ui.main.fragments.main.model.LocalPhoto
import com.google.common.base.Converter

object ApiConverter {
    var LOCAL_PHOTO: Converter<Photo, LocalPhoto> = object : Converter<Photo, LocalPhoto>() {
        override fun doForward(a: Photo): LocalPhoto {
            return LocalPhoto(
                a.farm,
                a.id,
                a.isfamily,
                a.isfriend,
                a.ispublic,
                a.owner,
                a.secret,
                a.server,
                a.title,
                "https://farm${a.farm}.staticflickr.com/${a.server}/${a.id}_${a.secret}_m.jpg"
            )
        }

        override fun doBackward(b: LocalPhoto): Photo {
            throw IllegalAccessException()
        }

    }

}
