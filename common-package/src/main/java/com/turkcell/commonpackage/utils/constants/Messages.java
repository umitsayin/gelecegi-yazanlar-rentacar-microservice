package com.turkcell.commonpackage.utils.constants;

public class Messages {
    public static String VALIDATION_EXCEPTION_MESSAGE = "Request not valid.";
    public static class Brand{
        public static final String BRAND_NOT_FOUND = "Marka bulunamadı!";
        public static final String BRAND_ALREADY_EXISTS = "Böyle bir marka mevcut!";
    }

    public static class Car{
        public static final String CAR_NOT_FOUND = "Araç bulunamadı!";
        public static final String CAR_IS_NOT_AVAILABLE = "Araç kirada olduğu için bakıma alınamaz!";
        public static final String PLATE_NOT_VALID = "plate number must match the pattern";
        public static final String PLATE_EXISTS = "PLATE_ALREADY_EXISTS";


    }

    public static class Invoice{
        public static final String INVOICE_NOT_FOUND = "Fatura bulunamadı!";
    }

    public static class Maintenance {
        public static final String NotExists = "MAINTENANCE_NOT_EXISTS";
        public static final String CarExists = "CAR_IS_CURRENTLY_UNDER_MAINTENANCE";
        public static final String CarNotExists = "CAR_NOT_REGISTERED_FOR_MAINTENANCE";
        public static final String CarIsRented = "CAR_IS_CURRENTLY_RENTED_AND_CANNOT_BE_SERVICED_FOR_MAINTENANCE";
    }

    public static class Model{
        public static final String MODEL_NOT_FOUND = "Model bulunamadı!";
        public static final String MODEL_ALREADY_EXISTS = "Böyle bir model mevcut!";
    }

    public static class Payment{
        public static final String PAYMENT_NOT_FOUND = "Ödeme bilgisi bulunamadı.";
        public static final String INSUFFICIENT_BALANCE = "Yetersiz bakiye.";
        public static final String CARD_NUMBER_ALREADY_EXISTS = "Kart numarası zaten kayıtlı.";
        public static final String CARD_INFORMATION_INCORRECT = "Kart bilgileriniz hatalı.";
    }

    public static class Rental{
        public static final String RENTAL_NOT_FOUND = "Kiralama bilgisine ulaşılamadı.";

    }


    private Messages(){

    }
}
