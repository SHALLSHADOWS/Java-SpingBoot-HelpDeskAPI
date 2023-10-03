package com.example.HelpDesk.Service;
import com.example.HelpDesk.Dto.CategoryDto;
import com.example.HelpDesk.Dto.SupportDto;
import com.example.HelpDesk.Model.Response;
import com.example.HelpDesk.Model.Support;

import java.util.List;

public interface SupportService {
    SupportDto createSupport(SupportDto supportDto);

    List<SupportDto> findAll();

    Response<SupportDto> finById(int id);

    Response<SupportDto> updateSupport(SupportDto supportDto, int id);

    Response deleteSupport(int id);
}
