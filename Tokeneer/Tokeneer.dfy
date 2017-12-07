class EnrollmentStation{

  method enroll(fingerpring : int) return(token : Token)

  {

  }

  method enroll(fingerprint : int, clearance : int) return(token : Token)
  {
  }

}

class Token{

  var id : int;
  var clearance : int;

  method init(id : int, clearance : int)
  requires 0 <= id;
  requires 0 <= clearance <= 3;
  ensures 0 <= id;
  ensures 0 <= clearance <= 3;
  modifies this;
  {
    this`id := id;
    this`clearance := clearance;
  }

  method setClearance(clearance : int) return( : bool)
  requires 0 <= clearance <= 3;
  ensures 0 <= clearance <= 3;
  modifies this`clearance;
  {
    this`clearance := clearance;
    if this`clearance == clearance then return true
    else return false
  }

  method getClearance() return(clearance : int){
    return this`clearance;
  }
}

class IDStation{

  var alarmActive : bool := false;

  method readFingerPrint() return(fingerPrint : int)
  {

  }

  method verifyUser(fingerPrint : int) return(correctUser : bool)

  {



  }

  method verifyClearance(door : Door, token : Token) return(correctClearance : bool)

  {}

  method alarmOn()
  requires this`alarmActive == false;
  ensures this`alarmActive == true;
  modifies this`alarmActive;
  {
    this`alarmActive := true;
  }

  method alarmOff()
  requires this`alarmActive == true;
  ensures this`alarmActive == false;
  modifies this`alarmActive;
  {
    this`alarmActive := false;
  }

  method open(door : Door) return(doorOpen : bool)

  {}

  method close(door : Door) return(doorClosed : bool)

  {}
}

class User{

  var fingerPrint : int;
  var token : Token;

  method init()
  modifies this;
  {}

}

class Door{

  var id : int;
  var requiredClearance : int;

  method init(id : int, clearance : int)
  requires 0 <= clearance <= 3;
  requires 0 <= id;
  ensures this`id <= 0;
  ensures 0 <= this`requiredClearance <= 3;
  modifies this;
  {
    this`id := id;
    requiredClearance := clearance;
  }
}