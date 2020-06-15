package com.example.projetofinal;

public class Local{
        private String Name;
        private String Localidade;

        public String getRuaShopping() {
                return RuaShopping;
        }

        public void setRuaShopping(String ruaShopping) {
                RuaShopping = ruaShopping;
        }

        public String getHorarioShopping() {
                return HorarioShopping;
        }

        public void setHorarioShopping(String horarioShopping) {
                HorarioShopping = horarioShopping;
        }

        private String RuaShopping;
        private String HorarioShopping;

        public Local() {

        }

        public String getName() {
                return Name;
        }

        public void setName(String name) {
                Name = name;
        }

        public String getLocalidade() {
                return Localidade;
        }

        public void setLocalidade(String localidade) {
                Localidade = localidade;
        }
}
