package com.jitendraalekar.match.data.source

import com.jitendraalekar.match.data.model.User
import com.jitendraalekar.match.network.model.NetworkUser

object NetworkUserToUserConverter {
    fun toUser(networkUser: NetworkUser) : User{
        return with(networkUser){
            User(
                uuid = login.uuid ,
                gender  = gender,
                title = name.title ,
                firstName = name.first  ,
                lastName  = name.last,
                location  = "${location.street.number} ${location.street.name}",
                city  =location.city,
                state  = location.state,
                country  = location.country,
                postCode  = location.postcode,
                latitude  =location.coordinates.latitude,
                longitude  = location.coordinates.longitude,
                timeZoneOffset  = location.timezone.offset,
                timeZoneDescription = location.timezone.description ,
                email = email ,
                dob = dob.date  ,
                age  = dob.age,
                largePicture = picture.large,
                mediumPicture = picture.medium,
                thumbnailPicture = picture.thumbnail,
                nat = nat

            )
        }
    }
}