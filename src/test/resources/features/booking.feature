Feature: Booking a cleaning service

  Scenario Outline: Create a new cleaning booking
    When Number of hrs is "<numberOfHrs>"
    And Number of cleaner is "<cleanerCount>"
    And Material section is "<materialOption>"
    And Location is "<location>"
    When Booking type is "<bookingType>"
    And Suggest cleaner <cleaner>
    And Date is "<date>" "<time>"
    And Cash payment

  Examples:
    |bookingType  |location     |materialOption |cleanerCount |numberOfHrs  |cleaner    |date             | time |
    |one time     |Dubai Marina |Yes            |1            |2            |1         |first available  |first available  |
    |weekly       |Dubai Marina |No             |2            |2             |0         |Tomorrow         |09:00-09:30                  |