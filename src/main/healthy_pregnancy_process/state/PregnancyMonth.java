package main.healthy_pregnancy_process.state;

public class PregnancyMonth {
        private int number;
        private String description;
        private String advice;

        public PregnancyMonth() {}

        public PregnancyMonth(int number, String description, String advice) {
            this.number = number;
            this.description = description;
            this.advice = advice;
        }

        // Геттеры и сеттеры
        public int getNumber() { return number; }
        public void setNumber(int number) { this.number = number; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getAdvice() { return advice; }
        public void setAdvice(String advice) { this.advice = advice; }
    }

