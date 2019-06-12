    final CheckBox ChckBoxNo = (CheckBox)promptsView.findViewById(R.id.ChkBoxNo);

                                  ChckBoxNo.setChecked(true);

                                   ChckBoxNo.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           if (ChckBoxNo.isChecked()) {

                                         ChckBoxNo.setChecked(false);
                                           }
                                           else if (!ChckBoxNo.isChecked())
                                           {
                                               ChckBoxNo.setChecked(true);
                                           }
                                       }
                                   });