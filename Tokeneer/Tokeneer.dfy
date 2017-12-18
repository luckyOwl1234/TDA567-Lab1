class EnrollmentStation{

  var users : set<int>;

  method init()
    modifies this`users;
  {
    users := {};
  }

  method enroll(fingerprint : int, clearance : int) returns(token : Token)
  {
  }
}

class Token{

  var id : int;
  var clearance : int;

  method init(id : int, clearance : int)
  requires 0 <= id;
  requires 1 <= clearance <= 3;
  ensures 0 <= id;
  ensures 1 <= clearance <= 3;
  modifies this`id;
  modifies this`clearance;
  {
    this.id := id;
    this.clearance := clearance;
  }

  method setClearance(clearance : int) returns (clearanceSet : bool)
  requires 0 <= clearance <= 3;
  ensures 0 <= clearance <= 3;
  modifies this`clearance;
  {
    this.clearance := clearance;
    if this.clearance == clearance
      {clearanceSet := true;}
    else
      {clearanceSet := false;}
  }

  method getClearance() returns(clearance : int){
    return this.clearance;
  }
}

class IDStation{

  var doorOpen: bool;
  var alarmActive: bool;

  method init()
    modifies this`doorOpen;
    modifies this`alarmActive;
  {
    doorOpen := false;
    alarmActive := false;
  }

  method readFingerPrint() returns (fingerPrint : int)
  {
  }

  method verifyUser(fingerPrint : int) returns (correctUser : bool)
  {
  }

  method verifyClearance(token : Token) returns (correctClearance : bool)
  {
  }

  method alarmOn()
  requires this.alarmActive == false;
  ensures this.alarmActive == true;
  modifies this`alarmActive;
  {
    this.alarmActive := true;
  }

  method alarmOff()
  requires this.alarmActive == true;
  ensures this.alarmActive == false;
  modifies this`alarmActive;
  {
    this.alarmActive := false;
  }

  method open() returns (doorOpen : bool)
  {
  }

  method close() returns (doorOpen : bool)
  {
  }
}

