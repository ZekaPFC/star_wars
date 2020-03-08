package com.marko.starwars.DI.scope

import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class FragmentScope {
}