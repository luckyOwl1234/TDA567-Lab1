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
  var fingerprint: int
  var invalidated: bool

  method init(id: int, clearance: int, fingerprint: int)
    requires 0 <= id
    requires 1 <= clearance <= 3
    requires 0 <= fingerprint
    modifies this`id, this`clearance, this`fingerprint, this`invalidated
    ensures 0 <= id
    ensures 1 <= clearance <= 3
    ensures invalidated == false;
    decreases id, clearance
  {
    this.id := id;
    this.clearance := clearance;
    this.fingerprint := fingerprint;
    this.invalidated := false;
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

  method invalidate() returns (invalidated: bool)
    requires clearance != 0
    modifies this`clearance
    ensures clearance == 0
  {
    if this.clearance != 0 {
      clearance := 0;
      invalidated := true;
    } else {
      invalidated := false;
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

