package com.bilgi.ie.thesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, Integer> mixingStage;
    private static List<String> availableColors = new ArrayList<>();

    private static Integer findMinAvailableColor() {
        Integer minStart = Integer.MAX_VALUE;
        int deletedIndex = 0;

        for (int i = 0; i < availableColors.size(); i++) {
            Integer current = mixingStage.get(availableColors.get(i));

            if (minStart > current) {
                minStart = current;
                deletedIndex = i;
            }
        }

        Integer result = mixingStage.get(availableColors.get(deletedIndex));
        availableColors.remove(deletedIndex);

        return result;
    }

    private static AvailableMachine findFirstAvailableMachine(
        List<AvailableMachine> availableMachines) {
        AvailableMachine currentAvailableMachine = null;

        Integer minEnd = Integer.MAX_VALUE; // technique to find minimum

        for (AvailableMachine availableMachine : availableMachines) {
            if (!availableMachine.getJobs().isEmpty()) {
                Job lastJob = availableMachine.getJobs().get(availableMachine.getJobs().size() - 1);
                Integer currentEnd = lastJob.getEndTime();
                if (minEnd > currentEnd) {
                    minEnd = currentEnd;
                    currentAvailableMachine = availableMachine;
                }
            } else {
                return availableMachine;
            }
        }

        return currentAvailableMachine;
    }

    public static void main(String[] args) {
        Stage stage1 = new Stage(1, "Stage-1");
        Stage stage2 = new Stage(2, "Stage-2");
        Machine machine1s1 = new Machine(1, "Machine-1", "Mixing", stage1);
        Machine machine2s1 = new Machine(2, "Machine-2", "Mixing", stage1);
        Machine machine3s1 = new Machine(3, "Machine-3", "Mixing", stage1);

        Machine machine1s2 = new Machine(1, "Machine-1", "Packing", stage2);
        Machine machine2s2 = new Machine(2, "Machine-2", "Packing", stage2);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of jobs to be processed:");
        Integer numberOfJobs = scanner.nextInt();
        String[] colors = new String[numberOfJobs];
        Double[] packageSizes = new Double[numberOfJobs];

        scanner.nextLine();

        for (int i = 0; i < numberOfJobs; i++) {
            System.out.println("Enter color and size separated with space:");
            String colorSize = scanner.nextLine();
            String[] colorAndSize = colorSize.split(" ");
            colors[i] = colorAndSize[0];
            availableColors.add(colorAndSize[0]);
            packageSizes[i] = Double.valueOf(colorAndSize[1]);
        }
        mixingStage = new HashMap<>();
        Map<Double, Integer> packaging = new HashMap<>();

        mixingStage.put("W", 10);
        mixingStage.put("L", 15);
        mixingStage.put("M", 20);
        mixingStage.put("B", 25);

        packaging.put(0.5, 30);
        packaging.put(1.0, 20);
        packaging.put(2.5, 15);
        packaging.put(5.0, 5);

        // stage 1
        AvailableMachine availableMachine1 = new AvailableMachine(machine1s1, new ArrayList<>());
        AvailableMachine availableMachine2 = new AvailableMachine(machine2s1, new ArrayList<>());
        AvailableMachine availableMachine3 = new AvailableMachine(machine3s1, new ArrayList<>());
        List<AvailableMachine> availableMachines = new ArrayList<>();
        availableMachines.add(availableMachine1);
        availableMachines.add(availableMachine2);
        availableMachines.add(availableMachine3);
        AvailableMachine availableMachine = findFirstAvailableMachine(availableMachines);

        for (int i = 0; i < numberOfJobs; i++) {
            Integer startTime = 0;
            Integer endTime = findMinAvailableColor();
            if (availableMachine.getJobs().size() != 0) {
                Job lastJob = availableMachine.getJobs()
                    .get(availableMachine.getJobs().size() - 1);
                startTime = lastJob.getEndTime();
                endTime += lastJob.getEndTime();
            }

            Job job = new Job(startTime, endTime);
            List<Job> jobs = availableMachine.getJobs();
            jobs.add(job);
            availableMachine.setJobs(jobs);

            availableMachine = findFirstAvailableMachine(availableMachines);
        }

        System.out.println("STAGE 1 --- MIXING");
        for (AvailableMachine machine : availableMachines) {
            System.out.print(machine.getMachine().getName());
            System.out.print("\t==>\t");
            for (Job job : machine.getJobs()) {
                System.out.print(job.getStartTime() + " | " + job.getEndTime() + " => ");
            }
            System.out.println();
        }
    }
}
