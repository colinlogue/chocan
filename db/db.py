import sqlite3
import csv

tables = {
    'service':  ['ServiceCode', 'Label', 'Fee'],
    'address':  ['AddressID', 'Street', 'City', 'State', 'ZIP'],
    'member':   ['MemberID', 'Name', 'IsActive', 'AddressID'],
    'provider': ['ProviderID', 'Name', 'AddressID'],
    'session':  ['SessionID', 'ProviderID', 'MemberID', 'ServiceCode', 'ServiceDate', 'LogTime', 'Comments']
}

if __name__ == "__main__":
    conn = sqlite3.connect('chocan.sqlite3')
    try:
        c = conn.cursor()
        for tablename, rownames in tables.items():
            filename = tablename + '.csv'
            with open(filename, 'w') as f:
                writer = csv.writer(f)
                writer.writerow(rownames)
                for row in c.execute('select * from ' + tablename):
                    writer.writerow(row)
        conn.commit()
    finally:
        conn.close()
