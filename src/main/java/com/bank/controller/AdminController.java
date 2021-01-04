package com.bank.controller;

import com.bank.bean.EmployeePojo;
import com.bank.constant.ApiConstant;
import com.bank.exception.BankException;
import com.bank.response.BankApiResponse;
import com.bank.service.AdminService;
import com.bank.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);


    @Autowired
    private AdminService adminService;

    @Autowired
    LoginService loginService;

    @GetMapping("login")
    public ResponseEntity<String> loginAdmin(@RequestParam @NotNull int userId) throws BankException {
        UUID uuid = loginService.generateAuthAdmin(userId);
        return ResponseEntity.ok(uuid.toString());
    }

    @GetMapping("logout")
    public ResponseEntity logoutAdmin(@RequestHeader("header") String header) throws BankException {

        loginService.deleteAuthAdmin(header);
        return ResponseEntity.ok( HttpStatus.OK);
    }

    @PostMapping("addEmployees")
    public ResponseEntity<BankApiResponse> addEmployees(@RequestBody @NotNull List<EmployeePojo> employeePojoList) throws BankException {
        LOG.info("addEmployees Api request params :{}", employeePojoList);
        String addSuccess = adminService.addEmployees(employeePojoList);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,addSuccess));
    }

    @PutMapping("deleteEmployees")
    public ResponseEntity<BankApiResponse> deleteEmployee(@RequestBody @NotNull String employeeId) throws BankException {
        LOG.info("deleteEmployee Api request params :{}", employeeId);
        String deleteSuccess = adminService.deleteEmployee(employeeId);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,deleteSuccess));
    }
}
