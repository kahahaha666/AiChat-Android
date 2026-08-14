package com.yourname.aichat.di

import com.yourname.aichat.core.security.BiometricAuth
import com.yourname.aichat.core.skill.SkillManager
import org.koin.dsl.module

val appModule = module {
    single { BiometricAuth(androidContext()) }
    single { SkillManager(androidContext()) }
}