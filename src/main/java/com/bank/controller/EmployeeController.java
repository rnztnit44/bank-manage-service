package com.bank.controller;

import com.bank.bean.AccountPojo;
import com.bank.bean.CustomerPojo;
import com.bank.constant.ApiConstant;
import com.bank.constant.BankConstant;
import com.bank.exception.BankException;
import com.bank.response.BankApiResponse;
import com.bank.service.EmployeeService;
import com.bank.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/employee/")
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<String> loginEmployee(@RequestParam("employeeId") int employeeId) throws BankException {
        UUID uuid = loginService.generateAuthEmployee(employeeId);
        return ResponseEntity.ok(uuid.toString());
    }

    @PutMapping("logout")
    public ResponseEntity<BankApiResponse> logoutEmployee(@RequestHeader("header") String header, @RequestParam("employeeId") int employeeId) throws BankException {
        String key = header + employeeId;
        String logoutSuccess = loginService.deleteAuthAdmin(key);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,logoutSuccess));
    }

    @PostMapping("customer")
    public ResponseEntity<BankApiResponse> addCustomer(@RequestBody @NotNull CustomerPojo customerPojo) throws BankException {
        LOG.info("addCustomer Api request params :{}", customerPojo);
        String addSuccess = employeeService.addCustomer(customerPojo);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,addSuccess));
    }

    @PostMapping("account")
    public ResponseEntity<BankApiResponse> createAccount(@RequestBody @NotNull AccountPojo accountRequest) throws BankException {
        LOG.info("createAccount Api request params :{}", accountRequest);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,employeeService.createAccount(accountRequest)));
    }

    @PostMapping("link/customer")
    public ResponseEntity<BankApiResponse> linkCustomerWithAccounts(@RequestParam  @NotNull int cId,@RequestParam @NotNull List<String> accountNos) throws BankException {
        LOG.info("Link Customer Api request params :{},{}", cId,accountNos);
        String accountLinkSuccess = employeeService.linkCustomerWithAccounts(cId,accountNos);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,accountLinkSuccess));
    }

    @PutMapping("kyc")
    public ResponseEntity<BankApiResponse> updateKycForCustomer(@RequestParam  @NotNull int cId,@RequestParam String aadhaar,@RequestParam String mobile,@RequestParam String email) throws BankException {
        LOG.info("updateKyc Api request for customerId :{}", cId);
        String updateSuccess = employeeService.updateKycForCustomer(cId,aadhaar,mobile,email);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,updateSuccess));
    }

    @GetMapping("customer")
    public ResponseEntity<CustomerPojo> GetDetailForCustomer(@RequestParam @NotNull int cId) throws BankException {
        LOG.info("get details Api request for customerId :{}", cId);
        return ResponseEntity.ok().body(employeeService.GetDetailForCustomer(cId));
    }



    @DeleteMapping("customer")
    public ResponseEntity<BankApiResponse> deleteCustomer(@RequestParam @NotNull int cId) throws BankException {
        LOG.info("deleteCustomer Api request for customerId :{}", cId);
        String deleteSuccess = employeeService.deleteCustomer(cId);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,deleteSuccess));
    }

    @GetMapping("account/balance")
    public ResponseEntity<BankApiResponse> accountBalanceForAccount(@RequestParam @NotNull String accountNo) throws BankException {
        LOG.info("accountDetailsForCustomer Api request params :{}", accountNo);
        int balanceAmount = employeeService.accountBalanceForAccount(accountNo);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE, BankConstant.ACCOUNT_BALANCE+balanceAmount));
    }

    @PutMapping("transfer/money")
    public ResponseEntity<BankApiResponse> transferMoney(@RequestParam @NotNull String sourceAccount,@RequestParam @NotNull String targetAccount,@RequestParam @NotNull int amount) throws BankException {
        LOG.info("transferMoney Api request params :{},{},{}", sourceAccount,targetAccount,amount);
        String deleteSuccess = employeeService.transferMoney(sourceAccount,targetAccount,amount);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,deleteSuccess));
    }

    @GetMapping("account/statement")
    public ByteArrayInputStream accountStatement(@RequestParam @NotNull String account,@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")@RequestParam("startTime")@NotNull Date startTime,@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")@RequestParam("endTime")@NotNull Date endTime) throws BankException {
        LOG.info("accountDetailsForCustomer Api request params :{}", account);
        return employeeService.accountStatement(account,startTime,endTime);
    }

    @PutMapping("account/interest")
    public ResponseEntity<BankApiResponse> calculateInterest(@RequestParam @NotNull String accountNo, @RequestParam @NotNull float interestRate) throws BankException {
        LOG.info("calculateInterest Api request params :{},{}", accountNo,interestRate);
        float interest = employeeService.calculateInterest(accountNo,interestRate);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE, BankConstant.INTEREST_CALCULATION+interest));
    }
}
