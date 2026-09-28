package smart.creditcardservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import smart.creditcardservice.entity.CreditCard;
import smart.creditcardservice.entity.request.CreditCardSaveRequest;
import smart.creditcardservice.entity.request.CreditCardUpdateRequest;
import smart.creditcardservice.utils.client.dto.BankResponse;
import smart.creditcardservice.utils.client.dto.RestResponse;
import smart.creditcardservice.utils.client.dto.UserResponse;
import smart.creditcardservice.utils.client.enums.Gender;

import java.time.LocalDate;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectFactory {
    private static ObjectFactory instance;
    private CreditCard creditCard;
    private CreditCardUpdateRequest creditCardUpdateRequest;
    private CreditCardSaveRequest creditCardSaveRequest;
    private List<CreditCard> creditCardList;
    private UserResponse userResponse;
    private BankResponse bankResponse;

    public static synchronized ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }

        return instance;
    }

    public CreditCard getCreditCard() {
        if (creditCard == null) {
            creditCard = CreditCard.builder()
                    .id(1L)
                    .cardNumber("54984387545")
                    .userId(1L)
                    .expirationDate(LocalDate.of(2001,1,1))
                    .cvv("1234")
                    .creditLimit(3650.50)
                    .debtAmount(1650.50)
                    .bankId(1L)
                    .build();
        }

        return creditCard;
    }

    public CreditCardUpdateRequest getCreditCardUpdateRequest() {
        if (creditCardUpdateRequest == null) {
            creditCardUpdateRequest = CreditCardUpdateRequest.builder()
                    .id(1L)
                    .cardNumber("54984387545")
                    .userId(1L)
                    .expirationDate(LocalDate.of(2001,1,1))
                    .cvv("1234")
                    .creditLimit(3650.50)
                    .debtAmount(1650.50)
                    .bankId(1L)
                    .build();
        }

        return creditCardUpdateRequest;
    }

    public CreditCardSaveRequest getCreditCardSaveRequest() {
        if (creditCardSaveRequest == null) {
            creditCardSaveRequest = CreditCardSaveRequest.builder()
                    .cardNumber("54984387545")
                    .userId(1L)
                    .expirationDate(LocalDate.of(2001,1,1))
                    .cvv("1234")
                    .creditLimit(3650.50)
                    .debtAmount(1650.50)
                    .bankId(1L)
                    .build();
        }

        return creditCardSaveRequest;
    }

    public List<CreditCard> getCreditCardList() {
        if (creditCardList == null) {
            CreditCard creditCardOne = CreditCard.builder()
                    .id(1L)
                    .cardNumber("54984387545")
                    .userId(1L)
                    .expirationDate(LocalDate.of(2001,1,1))
                    .cvv("1234")
                    .creditLimit(3650.50)
                    .debtAmount(1650.50)
                    .bankId(1L)
                    .build();

            CreditCard creditCardTwo = CreditCard.builder()
                    .id(2L)
                    .cardNumber("54984387545")
                    .userId(1L)
                    .expirationDate(LocalDate.of(2001,1,1))
                    .cvv("1234")
                    .creditLimit(3650.50)
                    .debtAmount(1650.50)
                    .bankId(1L)
                    .build();

            creditCardList = List.of(
                    creditCardOne,
                    creditCardTwo
            );
        }

        return creditCardList;
    }

    public UserResponse getUserResponse() {
        if (userResponse == null) {
            userResponse = UserResponse.builder()
                    .id(1L)
                    .username("esmart")
                    .password("121221")
                    .email("emree.smart@gmail.com")
                    .firstName("Emre")
                    .lastName("Ünaldı")
                    .phoneNumber("05078711189")
                    .birthDate(LocalDate.of(2001, 1, 1))
                    .gender(Gender.MALE)
                    .build();
        }

        return userResponse;
    }

    public BankResponse getBankResponse() {
        if (bankResponse == null) {
            bankResponse = BankResponse.builder()
                    .id(1L)
                    .bankName("Example Bank")
                    .bankCode("EXBK")
                    .branchName("Main Branch")
                    .branchCode("MB")
                    .accountNumber("1234567812345678")
                    .address("KAYSERI / Incesu")
                    .email("info@examplebank.com")
                    .phoneNumber("0507 871 11 89")
                    .build();
        }

        return bankResponse;
    }

    public <T> RestResponse<T> getRestResponse(T entityResponse) {
        return RestResponse.<T>builder()
                .data(entityResponse)
                .success(true)
                .build();
    }
}
