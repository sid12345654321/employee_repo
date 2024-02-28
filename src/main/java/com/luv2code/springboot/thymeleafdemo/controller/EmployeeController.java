package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/list")
     public String getEmployeeList(Model model){

        List<Employee> theEmployees = employeeService.findAll();



        model.addAttribute("employees",theEmployees);

        return "employees/list-employees";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

        employeeService.save(theEmployee);

        return "redirect:/employees/list";


    }


    @GetMapping("/showAddForm")
    public String showFormAdd(Model theModel){

        Employee theEmployee = new Employee();

        theModel.addAttribute("employee",theEmployee);

        return "/employees/employee-form";
    }

    @GetMapping("/showFormUpdate")
    public String showFormUpdate(@ModelAttribute("employeeId") int theId, Model theModel){

        System.out.println("id to be update "+ theId);

        Employee theEmployee = employeeService.findById(theId);

        theModel.addAttribute("employee" ,theEmployee);

        return "employees/employee-update-form";

    }

    @GetMapping("/delete")
    public String delete(@ModelAttribute("employeeId") int theId){

        employeeService.deleteById(theId);

        return "redirect:/employees/list";

    }


}
