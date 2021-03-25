package com.restapi.extremesportsapp.service;

import com.restapi.extremesportsapp.entity.Location;
import com.restapi.extremesportsapp.entity.Sport;
import com.restapi.extremesportsapp.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class SportServiceClass implements Service<Sport> {

    private final SportRepository sportRepository;

    @Autowired
    public SportServiceClass(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    //retrieving all sports

    public List<Sport> getAll(){

        return sportRepository.findAll();

    }

    //retrieving a sport

    @Override
    public Sport getOne(Integer id) {

        Optional<Sport> result = sportRepository.findById(id);

        Sport sport = null;

        if(result.isPresent()){
            sport = result.get();
        }else{
            throw new RuntimeException("Did not find the sport id - " + id);
        }


        return sport;
    }
    //adding a location to a region

    public void addLocation(Integer sportId, Location location){

        Optional<Sport> result = sportRepository.findById(sportId);

        Sport sport = null;

        if(result.isPresent()){
            sport = result.get();
            sport.addLocation(location);
            sportRepository.save(sport);
        }else{
            throw new RuntimeException("The id you requested for the sports id invalid " + sportId);
        }
    }


    //find all the sports from the start date and end date

    public List<Sport> findDates(String startDate , String endDate){

        String monthStart = startDate.substring(3);
        String monthEnd = endDate.substring(3);
        String dayStart = startDate.substring(0,2);
        String dayEnd = endDate.substring(0,2);

        List<Sport> allSports =  sportRepository.findAll(Sort.by(Sort.Direction.ASC,"mediumCost"));
        List<Sport> okSports = new ArrayList<>();

        for(Sport sport : allSports){

            String sportMonthStart = sport.getStartDate().substring(3);
            String sportMonthEnd = sport.getEndDate().substring(3);
            String sportDayStart = sport.getStartDate().substring(0,2);
            String sportDayEnd = sport.getEndDate().substring(0,2);

            if(sportMonthStart.compareTo(sportMonthEnd) < 0 && monthStart.compareTo(monthEnd) < 0){
                if(monthStart.compareTo(sportMonthStart) > 0 &&  monthEnd.compareTo(sportMonthEnd) < 0 && monthStart.compareTo(sportMonthEnd) < 0 &&  monthEnd.compareTo(sportMonthStart) > 0){
                    okSports.add(sport);
                }else if(monthStart.compareTo(sportMonthStart) == 0 &&  monthEnd.compareTo(sportMonthEnd) < 0){
                    if(dayStart.compareTo(sportDayStart) >= 0){
                        okSports.add(sport);
                    }
                }else if (monthStart.compareTo(sportMonthStart) > 0 &&  monthEnd.compareTo(sportMonthEnd) == 0){
                    if(dayStart.compareTo(sportDayStart) >= 0){
                        okSports.add(sport);
                    }
                }else if (monthStart.compareTo(sportMonthStart) == 0 &&  monthEnd.compareTo(sportMonthEnd) == 0) {
                    if (dayStart.compareTo(sportDayStart) >= 0 && dayEnd.compareTo(sportDayEnd) <= 0) {
                        okSports.add(sport);
                    }
                }
            }else if(sportMonthStart.compareTo(sportMonthEnd) > 0){ //if the period start with a month and ends after the start of next year
                    if(monthStart.compareTo(sportMonthStart) > 0 && monthEnd.compareTo(sportMonthStart) > 0 ){ // if the period is before the end of the year but after the start month
                        okSports.add(sport);
                    }else if(monthStart.compareTo(sportMonthEnd) < 0 && monthEnd.compareTo(sportMonthEnd) < 0){ //if the period is after the end of year but before the end of end month
                        okSports.add(sport);
                    }else if(monthStart.compareTo(sportMonthEnd) > 0 && monthEnd.compareTo(sportMonthStart) < 0 ){ //if the period start before the end of the year but ends after and year and it
                        okSports.add(sport);                                                                        //is in the sports period
                    }else if(monthStart.compareTo(sportMonthStart) == 0){ //if the starting period is the same as the starting sport period
                        if((monthEnd.compareTo(sportMonthStart) >= 0 || monthEnd.compareTo(sportMonthEnd)< 0) && dayStart.compareTo(sportDayStart) >= 0 ){ //then if the end period end before the end of the year or before the end of the sport period
                            okSports.add(sport);                                                                        //and if the start day of the period si after the start date of sport
                        }else if (monthEnd.compareTo(sportMonthEnd) == 0){
                            if (dayStart.compareTo(sportDayStart) >= 0 && dayEnd.compareTo(sportDayEnd) <= 0) {
                                okSports.add(sport);
                            }
                        }
                    }else if(monthEnd.compareTo(sportMonthEnd) == 0){
                        if((monthStart.compareTo(sportMonthEnd) <= 0 || monthStart.compareTo(sportMonthStart) > 0) && dayEnd.compareTo(sportDayEnd) <= 0 ){
                            okSports.add(sport);
                        }else if (monthStart.compareTo(sportMonthStart) == 0){
                            if (dayStart.compareTo(sportDayStart) >= 0 && dayEnd.compareTo(sportDayEnd) <= 0) {
                                okSports.add(sport);
                            }
                        }
                    }
            }
        }
        return okSports;
    }

    //saving a sport to the database

    @Override
    public void saveItem(Sport sport) {

        sportRepository.save(sport);

    }

    //deleting a sport from the database

    @Override
    public void deleteItemById(Integer id) {

        sportRepository.deleteById(id);
    }

}
