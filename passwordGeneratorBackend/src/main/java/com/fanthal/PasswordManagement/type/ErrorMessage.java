package com.fanthal.PasswordManagement.type;

import lombok.Getter;

@Getter
public enum ErrorMessage {

	NOT_FOUND("SDD-001", "İlgili kayıt bulunmamaktadır.", "Veritabanında aranan kayıt bulunmamaktadır."),
	REFRESH_TOKEN_NOT_FOUND("SDD_101", "Refresh token bulunamadı.", "Refresh token bulunamadı."),
	REFRESH_TOKEN_EXPIRED("SDD_102", "Refresh token süresi dolmuş.", "Refresh token süresi dolmuş."),
	USER_NOT_FOUND("SDD_201", "Kullanıcı bulunamadı.", "Kullanıcı bulunamadı."),
	USER_WRONG_OLD_PASSWORD("SDD_202", "Eski şifreniz doğru değil.", "Eski şifre veritabanındaki şifre değil."),
	USER_SAME_PASSWORD_WITH_OLD_PASSWORD("SDD_203", "Eski şifre ve yeni şifre aynı olamaz", "Eski şifre ile yeni şifre aynı."),
	USER_EXIST("SDD_204", "Sistemde bu kullanıcı adına sahip başka kullanıcı var.", "Sisteme kayıtlı kullanıcı  adı."),
	USER_REGISTER_OPERATION_UNKNOWN("SDD_205", "İşlem türü bulunamadı.", "Yanlış işlem türü."),
	USER_NOT_ACTIVE("SDD_206", "Kullanıcı onaylanmamış yada aktif değil. Lütfen sistem yöneticisine ulaşın.", "Aktif olmayan kullanıcı."),
	USER_NOT_AUTHORIZED("SDD_206", "Bu işlem için yetkiniz bulunmamakta.", "Yetkisiz kullanıcı."),
	PASSWORD_EXIST("SDD_301", "Sistemde bu isimde başka bir şifre kayıtlı.", "Sistemde bu isimde başka bir şifre kayıtlı."),
	PASSWORD_NOT_EXIST("SDD_302", "Şifre bulunamadı.", "Şifre bulunamadı."),
	NOT_HAVE_PERMISSION_FOR_DELETE("SDD_303", "Şifre silmek için yetkin yok.", "Şifre silmek için yetkin yok."),;

	private final String code;
	private final String message;
	private final String internalMessage;

	ErrorMessage(String code, String message, String internalMessage) {
		this.code = code;
		this.message = message;
		this.internalMessage = internalMessage;
	}

}
