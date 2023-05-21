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

    public static class Payment {
        public static final String NotFound = "PAYMENT_NOT_FOUND";
        public static final String CardNumberAlreadyExists = "CARD_NUMBER_ALREADY_EXISTS";
        public static final String NotEnoughMoney = "NOT_ENOUGH_MONEY";
        public static final String NotAValidPayment = "NOT_A_VALID_PAYMENT";
        public static final String Failed = "PAYMENT_FAILED";

    }

    public static class Rental{
        public static final String RENTAL_NOT_FOUND = "Kiralama bilgisine ulaşılamadı.";

    }


    private Messages(){

    }
}
