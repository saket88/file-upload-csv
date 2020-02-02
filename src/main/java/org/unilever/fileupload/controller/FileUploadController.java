package org.unilever.fileupload.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.unilever.fileupload.domain.UserLocationDataEntity;
import org.unilever.fileupload.model.UserLocationData;
import org.unilever.fileupload.repository.UserLocationDataRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    @Autowired
    UserLocationDataRepository userLocationDataRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload-csv")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<UserLocationData> csvToBean = getBeanFrom(reader);
                List<UserLocationData> userLocations = getDistinctUserLocations(csvToBean);
                List<UserLocationDataEntity> userLocationDataEntities = getUserLocationDataEntities(userLocations);
                model.addAttribute("userLocations", getSavedUserLocations(userLocationDataEntities));
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "index";
    }

    private List<UserLocationDataEntity> getSavedUserLocations(List<UserLocationDataEntity> userLocationDataEntities) {
        return userLocationDataRepository.saveAll(userLocationDataEntities);
    }

    private List<UserLocationDataEntity> getUserLocationDataEntities(List<UserLocationData> userLocations) {
        List<UserLocationDataEntity> userLocationDataEntities= new ArrayList<>();
        constructUserLocationDataEntities(userLocations, userLocationDataEntities);
        return userLocationDataEntities;
    }

    private void constructUserLocationDataEntities(List<UserLocationData> userLocations, List<UserLocationDataEntity> userLocationDataEntities) {
        for (UserLocationData userLocationData : userLocations) {
            userLocationDataEntities.add(
                    UserLocationDataEntity.builder()
                            .id(userLocationData.getId())
                            .lastName(userLocationData.getLastName())
                            .location(userLocationData.getLocation())
                            .outletName(userLocationData.getOutletName())
                            .outletType(userLocationData.getOutletType())
                            .build()
            );
        }
    }

    private List<UserLocationData> getDistinctUserLocations(CsvToBean<UserLocationData> csvToBean) {
        return csvToBean.parse().stream().distinct().collect(Collectors.toList());
    }

    private CsvToBean<UserLocationData> getBeanFrom(Reader reader) {
        return (CsvToBean<UserLocationData>) new CsvToBeanBuilder(reader)
                            .withType(UserLocationData.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
    }
}
