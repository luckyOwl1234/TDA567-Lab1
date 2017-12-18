// Dafny 2.0.0.00922 technical preview 0
// Command Line Options: Tokeneer/Tokeneer.dfy
// Tokeneer.dfy

class EnrollmentStation {
  var users: set<int>

  method init()
    modifies this`users
  {
    users := {};
  }

  method enroll(fingerprint: int, clearance: int) returns (token: Token)
    decreases fingerprint, clearance
  {
  }
}

class Token {
  var id: int
  var clearance: int

  method init(id: int, clearance: int)
    requires 0 <= id
    requires 1 <= clearance <= 3
    modifies this`id, this`clearance
    ensures 0 <= id
    ensures 1 <= clearance <= 3
    decreases id, clearance
  {
    this.id := id;
    this.clearance := clearance;
  }

  method setClearance(clearance: int) returns (clearanceSet: bool)
    requires 0 <= clearance <= 3
    modifies this`clearance
    ensures 0 <= clearance <= 3
    decreases clearance
  {
    this.clearance := clearance;
    if this.clearance == clearance {
      clearanceSet := true;
    } else {
      clearanceSet := false;
    }
  }

  method getClearance() returns (clearance: int)
  {
    return this.clearance;
  }
}

class IDStation {
  var doorOpen: bool
  var alarmActive: bool

  method init()
    modifies this`doorOpen, this`alarmActive
  {
    doorOpen := false;
    alarmActive := false;
  }

  method readFingerPrint() returns (fingerPrint: int)
  {
  }

  method verifyUser(fingerPrint: int) returns (correctUser: bool)
    decreases fingerPrint
  {
  }

  method verifyClearance(token: Token) returns (correctClearance: bool)
    decreases token
  {
  }

  method alarmOn()
    requires this.alarmActive == false
    modifies this`alarmActive
    ensures this.alarmActive == true
  {
    this.alarmActive := true;
  }

  method alarmOff()
    requires this.alarmActive == true
    modifies this`alarmActive
    ensures this.alarmActive == false
  {
    this.alarmActive := false;
  }

  method open() returns (doorOpen: bool)
  {
  }

  method close() returns (doorOpen: bool)
  {
  }
}

